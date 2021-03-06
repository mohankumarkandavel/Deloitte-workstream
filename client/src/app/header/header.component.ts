import {Component, OnInit} from '@angular/core';
import * as $ from 'jquery';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  user: string;

  constructor() {
  }

  ngOnInit() {
    this.getCurrentlyLoggedInUser();
  }

  getCurrentlyLoggedInUser() {
    this.user = localStorage.getItem("name");
  }
}
