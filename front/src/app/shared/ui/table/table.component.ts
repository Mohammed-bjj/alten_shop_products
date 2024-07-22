import { formatDate } from '@angular/common';
import { Component, EventEmitter, Input, OnChanges, Output, SimpleChanges, ViewChild } from '@angular/core';
import { isArray } from '@dwtechs/checkhard';
import { CrudItemOptions } from 'app/shared/utils/crud-item-options/crud-item-options.model';
import { TableLazyLoadEvent } from 'app/shared/ui/table/table-lazyload-event.model';
import { LazyLoadEvent, SelectItem } from 'primeng/api';
import { Table } from 'primeng/table';
import { TableColumn } from './table-column.model';
import { ControlType } from 'app/shared/utils/crud-item-options/control-type.model';
import { ProductsResponse } from '../list/search.model';
import { ProductServiceHttp } from 'app/shared/utils/services/produits.service';
import { TABLE_CONFIG } from './table-config';
import { Product } from 'app/models/product.model';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss'],
})
export class TableComponent<T extends Product> implements OnChanges {
  @ViewChild('dataTable') dataTable: Table;
  @Input() public  data: T[] = [];

  // col.filterable && col.isVisible && !col.hidden
  @Input() public readonly config: CrudItemOptions[] = TABLE_CONFIG; // config for table 
  @Input() public readonly editableRows: boolean = true;
  @Input() public readonly deletableRows: boolean = false;
  @Input() public readonly selectable: boolean = true;
  @Input() public readonly allowAdd: boolean = true;
  @Input() public readonly allowDelete: boolean = true;
  @Input() public readonly allowEdit: boolean = true;
  @Input() public entity:  new () => T ; // class of new entry
  @Input() public readonly lazy: boolean = false;
  @Input() public readonly totalRecords: number;
  @Input() public readonly multiSelect: boolean;
  @Input() public  errorMessage: string;
  @Input() public  titleHeader: string;

  @Output() saved: EventEmitter<T> = new EventEmitter();
  @Output() deleted: EventEmitter<number[]> = new EventEmitter();
  @Output() lazyLoaded: EventEmitter<TableLazyLoadEvent> = new EventEmitter();

  public cols: TableColumn[];
  public selectedEntries = [];
  public columnsConfigDialogDisplayed = false;
  public exportDialogDisplay = false;
  public entryEditionDialogDisplayed = false;

  public editedEntry: T ;
  public creation: boolean;
  public ControlType = ControlType;

  constructor(    private readonly  produitServcie : ProductServiceHttp,     private readonly route: ActivatedRoute
  ) {
    this.loadProducts();
    this.cols = this.getColumns(); 
  }


  private loadProducts(): void {
    this.route.data.subscribe( (data: Product) => {
      this.entity = data['data'];
    });
    
    this.produitServcie.getProducts().subscribe( (products: ProductsResponse) => {       
      this.data = products.data as [T];
    });

  }
  ngOnChanges(changes: SimpleChanges): void {
    console.log('hello on change ')
    const { currentValue: config, previousValue: prevConfig } = changes.config ?? {};
    const configChanged = JSON.stringify(config) !== JSON.stringify(prevConfig);
    if (config && configChanged) {}

    const { currentValue: data, previousValue: prevData } = changes.data ?? {};
    if (data && prevData) {


    }
  }

  public onLazyLoad(event: LazyLoadEvent) {
    const cleanEvent: TableLazyLoadEvent = {
      first: event.first,
      rows: event.rows,
      sortOrder: event.sortOrder as 1 | -1,
      sortField: event.sortField,
      filters: event.filters
      // filters: event.filters ? JSON.stringify(event.filters) : '' as any
    };
    this.lazyLoaded.emit(cleanEvent);
  }

  public onEdit(rowData: T): void {
    this.titleHeader = `${rowData.name} Details`
    this.editedEntry = {...rowData};
    this.creation = false ;
    this.entryEditionDialogDisplayed = true;

  }

  public onDelete(id: number): void {
    this.produitServcie.deleteOneroduct(id).subscribe( {
      next: (res) => {
          this.data = this.data.filter( p => p.id != id);
      },
      error: (err) => {
        console.log("error delete : ", err.error);
      }
    } );
    this.deleted.emit([id]);
  }

  public onDeleteMultiple(): void {
    const ids = this.selectedEntries.map(entry => entry.id);
    switch((ids.length == this.data.length)){
      case true :
        this.produitServcie.deleteAllProducts().subscribe({
          next: (resp) => this.data = null,
          error: (err) => console.log("Error deleting products : ", err.error)
        })
        break;
      default: 
        ids.forEach( id => {
          this.produitServcie.deleteOneroduct(id).subscribe({
              next: (resp) => this.data = this.data.filter( p => p.id != id),
              error: (err) => console.log("Error deleting product : ", err.error)
          })
      })
    }
    this.deleted.emit(ids);
  }

  public manageColumns(): void {
    this.columnsConfigDialogDisplayed = true;
  }

  public onNew(): void {
    this.titleHeader = "Add New Product";
    this.entryEditionDialogDisplayed = true;
    this.creation = true;
    this.editedEntry = new this.entity() ;
  }

  public onEditedEntrySave(editedEntry): void {
    if(this.creation){
      this.produitServcie.saveProduct(editedEntry).subscribe({
        next: (data: Product) => {
          this.data.push(data as T);
          this.errorMessage = "Product added successfully";
        },
        error: (err) =>  {  console.log(err.error);  this.errorMessage = err.error }
      });
    }else {
      editedEntry = {
        ...editedEntry,
        id: this.editedEntry.id
      }
      this.produitServcie.updateProduct(editedEntry).subscribe(
        {
          next: (data: Product) =>
             {
               this.data.forEach( (product: Product) => 
                {
                 if(data.id == product.id){
                  product.code = data.code;
                  product.name = data.name;
                 }
                });
              },
          error: (err) => 
            {
              console.log("error : ", err.error);
              this.errorMessage = err.error;
            } 
        })
    }
    this.saved.emit(editedEntry);
    this.editedEntry = null;
    this.entryEditionDialogDisplayed = false;
  }
  
  public onDeleteEntry(id: number): void{
    this.deleted.emit([id])
    this.entryEditionDialogDisplayed = false;
  }

  public hideDialog(): void {
    this.titleHeader = "";
    this.columnsConfigDialogDisplayed = false;
    this.entryEditionDialogDisplayed = false;
    this.exportDialogDisplay = false;
    this.editedEntry = null;
    this.selectedEntries = [];
  }

  public export():void{
    this.exportDialogDisplay = true;
  }

  private getColumns(): TableColumn[] {
    return this.config.map(item => {
      const renderedValue = (cellValue: unknown, isTooltip = false) => this.getRenderer(cellValue, item, isTooltip);
      const columnOptions = item.columnOptions;
      return {
        ...item,
        ...columnOptions,
        key: item.key.toString(),
        isList: item.controlType === 'table',
        isVisible: columnOptions.default,
        renderer: (cellValue: unknown) => renderedValue(cellValue),
        tooltip: cellValue => columnOptions.tooltip ? columnOptions.tooltip(cellValue) : renderedValue(cellValue, true),
        filterable: columnOptions.filterable !== false,
        sortable: columnOptions.sortable !== false,
      }
    });
  }

  private getRenderer(cellValue: unknown, control: CrudItemOptions, isTooltip: boolean): string {
    if (control.columnOptions.customCellRenderer) {
      return control.columnOptions.customCellRenderer(cellValue);
    }
    switch (control.controlType) {
      case ControlType.TABLE: {
        return this.tableCellRenderer(cellValue);
      }
      case ControlType.SELECT: {        
        return this.selectCellRenderer(cellValue, control);
      }
      case ControlType.MULTISELECT: {
        return this.multiselectCellRenderer(cellValue, control, isTooltip);
      }
      case ControlType.DATE: {
        return formatDate(cellValue as number, 'yyyy-MM-dd', 'en');
      }
      case ControlType.CHECKBOX: {
        return this.checkboxCellRenderer(cellValue, isTooltip);
      }
      default: {
        return `${cellValue}`;
      }
    }
  }

  private tableCellRenderer(cellValue: unknown): string {
    return this.isCellArray(cellValue) ? cellValue.length.toString() : '';
  }
  
  private selectCellRenderer(cellValue: unknown, column: CrudItemOptions): string {
    const option = this.getOption(column, cellValue);
    if (!option) return '';
    return option.label;
  }

  private multiselectCellRenderer(cellValue: unknown, column: CrudItemOptions, tooltip: boolean): string {
    if (this.isCellArray(cellValue)) {
      const separator = tooltip ? ', ': '';
      let values = cellValue.map(val => this.getOption(column, val)).filter(val => !!val);
      return values.map(val => {
        if (val.styleClass && !tooltip) {
          return `<i class="${val.styleClass}">${val.label}</i>`;
        }
        return val.label;
      }).join(separator);
    }
    return '';
  }

  private checkboxCellRenderer(cellValue: unknown, tooltip: boolean): string {
    if (tooltip) {
      return cellValue ? 'Yes' : 'No';
    }
    return cellValue ? '<i class="pi pi-check green"></i>' : '<i class="pi pi-times indigo"></i>';
  }

  private isCellArray(cellValue: unknown): cellValue is unknown[] {
    return cellValue && isArray(cellValue);
  }

  private getOption(column: CrudItemOptions, cellValue: unknown): SelectItem {
    return column.options.find(opt => opt.value === cellValue)
  }

}
