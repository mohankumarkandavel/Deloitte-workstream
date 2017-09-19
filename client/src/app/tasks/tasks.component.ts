import { Component, OnInit } from '@angular/core';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import {Http} from '@angular/http';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {

  constructor(private modalService: NgbModal, private router: Router, private http: Http) {
  }

  private tasks: any[];

  ngOnInit() {
    this.http.get("http://localhost:8080/task" ).subscribe(
      (response) => {
        if(response.ok) {
          this.tasks = JSON.parse(response.text());
        }
      }
    );
  }

  newTask(id: string){
    this.modalService.open(id);
    console.log("found");
  }

  logout() {
    this.router.navigateByUrl("/home");
  }
}
