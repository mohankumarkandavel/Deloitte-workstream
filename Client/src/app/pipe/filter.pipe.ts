import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filter'
})
export class FilterPipe implements PipeTransform {

  transform(list: any[], filterField: string, keyword: string): any {
    if (!keyword || !filterField) {
      console.log('no');
      return list;
    }
    return list.filter( item => {
      console.log('cool');
      let fieldValue = item[filterField];
      return fieldValue.indexOf(keyword) >= 0;
    });
    // else (keyword) {
    //   // keyword = keyword.toLowerCase();
    //   return list.filter(function (el: any) {
    //     // return el.toLowerCase().indexOf(keyword) > -1;
    //     return el.indexOf(keyword) >= 0;
    //   });
    // }
  //   return list;
  }
  // transform(items: any[], keyword: string): any[] {
  //   if (!items)return [];
  //   if (!keyword) return items;
  //   keyword = keyword.toLowerCase();
  //   return items.filter( it => {
  //     return it.toLowerCase().includes(keyword);
  //   });
  // }

}
