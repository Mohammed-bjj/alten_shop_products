export interface SearchParams {
    first: number;
    rows: number;
    sortField: string;
    sortOrder: string;
    search: string;
    from: number;
    to: number;
    parentId?: string;
  }

  export const DEFAULT_SEARCH_PARAMS: SearchParams = {
    first: 0,
    rows: 10,
    sortField: 'name',
    sortOrder: Sort.asc,
    search: '',
    from: null,
    to: null
  }

  export const enum Sort {
    asc = 'asc',
    desc = 'desc'
  }




  export interface Product {
    id: number;
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