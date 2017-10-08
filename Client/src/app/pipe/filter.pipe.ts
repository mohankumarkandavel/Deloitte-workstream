import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filter'
})
export class FilterPipe implements PipeTransform {

  transform(list: any[], filterField: string, keyword: string): any {
    if (!keyword || !filterField) {
      // console.log('no');
      return list;
    }
    return list.filter( item => {
      // console.log('cool');
      let fieldValue = item[filterField];
      return fieldValue.indexOf(keyword) >= 0;
    });
  }
}
