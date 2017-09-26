import {Component, Input} from '@angular/core';
import {TaskService} from "../services/task.service";
import {Task} from "./task.model";

@Component({
  selector: 'app-team-member-card',
  templateUrl: './team-member-card.component.html',
  styleUrls: ['./team-member-card.component.css'],
  // inputs: ['task']
})

export class TeamMemberCardComponent {
  @Input()
  task: Task;
  shown: string = 'NONE';

  constructor(private taskService:TaskService) {
  }

  onTaskAccept() {
    this.taskService.updateTaskStatus(this.task, "Assigned");
  }
}
