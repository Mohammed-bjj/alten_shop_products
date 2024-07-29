
 class ProductBase {
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


export class Product extends ProductBase {
  id: number;

}





  export interface NewProductRequest extends ProductBase {

  }
  export interface ProductResponse {
    data: Product[];
  }



  export enum InventoryStatus {
    OUTOFSTOCK = "OUTOFSTOCK",
    LOWSTOCK = "LOWSTOCK", 
    INSTOCK ="INSTOCK",

  }


  export enum Category {
    ACCESSORIES = "Accessories",
    FITNESS = "Fitness", 
    ELECTRONICS ="Electronics",
    CLOTHING ="Clothing",
  }




  export enum ControlType {
    AUTOCOMPLETE = 'autocomplete',
    CHECKBOX = 'checkbox',
    DATE = 'date',
    INPUT = 'input',
    LIST = 'list',
    MULTISELECT = 'multiselect',
    SELECT = 'select',
    TABLE = 'table'
  }