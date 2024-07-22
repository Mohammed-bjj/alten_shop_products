import { CrudItemOptions } from 'app/shared/utils/crud-item-options/crud-item-options.model';
import { ControlType } from 'app/shared/utils/crud-item-options/control-type.model';
import { TypeInput } from 'app/shared/utils/crud-item-options/type.model';

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