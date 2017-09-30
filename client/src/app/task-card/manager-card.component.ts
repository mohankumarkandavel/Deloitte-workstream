import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-manager-card',
  templateUrl: './manager-card.component.html',
  styleUrls: ['./manager-card.component.css'],
  inputs: ['task']
})
export class ManagerCardComponent implements OnInit {

  constructor() {
  }

  ngOnInit() {
  }

}
