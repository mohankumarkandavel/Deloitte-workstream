import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-task-card',
  templateUrl: './task-card.component.html',
  styleUrls: ['./task-card.component.css'],
  inputs: ['task']
})
export class TaskCardComponent implements OnInit {

  constructor() {

  }

  ngOnInit() {
  }

}
