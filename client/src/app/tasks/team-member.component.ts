import {Component, OnInit} from '@angular/core';
import {TaskService} from '../services/task.service';

@Component({
  selector: 'app-team-member',
  templateUrl: './team-member.component.html',
  styleUrls: ['./tasks.component.css']
})

export class TeamMemberComponent implements OnInit {

  constructor(private taskService: TaskService) {
  }

  ngOnInit(): void {
    this.getAllTasks();
  }

  getAllTasks() {
    let userId = localStorage.getItem("userId");
    this.taskService.getUsersTasks(userId);
  }
}
