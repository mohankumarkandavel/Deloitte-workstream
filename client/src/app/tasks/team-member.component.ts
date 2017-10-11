import {Component, OnInit} from '@angular/core';
import {TaskService} from '../services/task.service';
import 'rxjs/Rx';
import {FormControl} from "@angular/forms";

@Component({
  selector: 'app-team-member',
  templateUrl: './team-member.component.html',
  styleUrls: ['./tasks.component.css']
})

export class TeamMemberComponent implements OnInit {
  public  keyword: string; // key word for searching
  public  titleFilter: FormControl = new FormControl();
  constructor(private taskService: TaskService) {
    this.titleFilter.valueChanges
      .debounceTime(500)
      .subscribe(
        value => this.keyword = value
      );
  }

  ngOnInit(): void {
    this.getAllTasks();
  }

  getAllTasks() {
    let userId = localStorage.getItem("userId");
    this.taskService.getUsersTasks(userId);
  }
}
