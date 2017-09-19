import { Component, OnInit } from '@angular/core';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector:'app-home-page',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomePageComponent implements OnInit {

  constructor(private modalService: NgbModal) {
  }

  ngOnInit() {
  }
  newTask(id: string){
    this.modalService.open(id);
    console.log("found");
  }

}
