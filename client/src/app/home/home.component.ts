import { Component, OnInit } from '@angular/core';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';

@Component({
  selector:'app-home-page',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomePageComponent implements OnInit {

  constructor(private modalService: NgbModal, private router: Router) {
  }

  ngOnInit() {
  }
  newTask(id: string){
    this.modalService.open(id);
    console.log("found");
  }

  logout() {
    this.router.navigateByUrl("/login");
  }
}
