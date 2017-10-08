import { Component, OnInit, Input } from '@angular/core';
import { Task } from '../tasks/task.model';


@Component({
  selector: 'app-manager-card',
  templateUrl: './manager-card.component.html',
  styleUrls: ['./manager-card.component.css'],
})
export class ManagerCardComponent implements OnInit {

  @Input()
  private task: Task;

  constructor() {
  }

  ngOnInit() {
  }

  private getTooltipRequestMoreInfo() : string {
    let tooltip:string = "";
    let requestees: any[] = this.task.requestedAssignees.filter(
      (requestedAssignee) => this.task.requestsById.indexOf(+requestedAssignee.id) !== -1
    );
    
    let onlyOne: boolean = true;

    for (let i = 0; i < requestees.length - 1; i++) {
      onlyOne = false;
      tooltip += requestees[i].name;
      tooltip += ", ";
    }

    tooltip += requestees[requestees.length - 1].name;
    tooltip += " ";
    tooltip += onlyOne ? "has" : "have";
    tooltip += " requested more information";

    return tooltip;
  }

  private taskHasRequests(): boolean {
    return this.task.requestsById.length > 0;
  }

}
