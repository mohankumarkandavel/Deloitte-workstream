import { Component, OnInit } from '@angular/core';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {

  constructor(private modalService: NgbModal, private router: Router) {
  }

  ngOnInit() {
  }
  newTask(id: string){
    this.modalService.open(id);
    console.log("found");
  }

  logout() {
    this.router.navigateByUrl("/home");
  }
}
