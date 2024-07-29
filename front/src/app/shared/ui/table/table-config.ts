import { CrudItemOptions } from 'app/shared/utils/crud-item-options/crud-item-options.model';
import { ControlType } from 'app/shared/utils/crud-item-options/control-type.model';
import { TypeInput } from 'app/shared/utils/crud-item-options/type.model';
import { Category, InventoryStatus } from 'app/models/product.model';

export const TABLE_CONFIG: CrudItemOptions[] = [
   { key: 'code',
     label: 'Code',
     controlType: ControlType.INPUT,
     columnOptions: { sortable: true, default: true, filterable: true },
     type: TypeInput.TEXT
   },
   { 
    key: 'name', 
    label: 'Name',
    controlType: ControlType.INPUT, 
    columnOptions: { sortable: true, default: true, filterable: true },
    type: TypeInput.TEXT
   },
];



export class Product {
  id?: number
  code: string;
  name: string;
  description: string;
  price: number;
  quantity: number;
  inventoryStatus: string;
  category: string;
  image?: string;
  rating?: number;
  }

export const TABLE_CONFIG_EDIT: CrudItemOptions[] = [
  { key: 'code',
    label: 'Code',
    controlType: ControlType.INPUT,
    columnOptions: { sortable: true, default: true, filterable: true },
    type: TypeInput.TEXT
  },
  { 
   key: 'name', 
   label: 'Name',
   controlType: ControlType.INPUT, 
   columnOptions: { sortable: true, default: true, filterable: true },
   type: TypeInput.TEXT
  },
  { 
    key: 'inventoryStatus', 
    label: 'inventoryStatus',
    controlType: ControlType.SELECT, 
    columnOptions: { sortable: true, default: true, filterable: true },
    type: TypeInput.TEXT,
    options: [  
      { label: InventoryStatus.INSTOCK, value: InventoryStatus.INSTOCK},  
      { label: InventoryStatus.LOWSTOCK, value: InventoryStatus.LOWSTOCK}, 
      { label: InventoryStatus.OUTOFSTOCK, value: InventoryStatus.OUTOFSTOCK},]
   },

  { 
    key: 'description', 
    label: 'description',
    controlType: ControlType.INPUT, 
    columnOptions: { sortable: true, default: true, filterable: true },
    type: TypeInput.TEXT
   },
   { 
    key: 'category', 
    label: 'category',
    controlType: ControlType.SELECT, 
    columnOptions: { sortable: true, default: true, filterable: true },
    type: TypeInput.TEXT,
    options: [  
      { label: Category.ACCESSORIES, value: Category.ACCESSORIES},  
      { label: Category.ELECTRONICS, value: Category.ELECTRONICS}, 
      { label: Category.FITNESS, value: Category.FITNESS},
      { label: Category.CLOTHING, value: Category.CLOTHING},]
   },

   { 
    key: 'price', 
    label: 'price',
    controlType: ControlType.INPUT, 
    columnOptions: { sortable: true, default: true, filterable: true },
    type: TypeInput.NUMBER
   },


   { 
    key: 'quantity', 
    label: 'quantity',
    controlType: ControlType.INPUT, 
    columnOptions: { sortable: true, default: true, filterable: true },
    type: TypeInput.NUMBER
   },
   { 
    key: 'rating', 
    label: 'rating',
    controlType: ControlType.INPUT, 
    columnOptions: { sortable: true, default: true, filterable: true },
    type: TypeInput.NUMBER
   }
];



