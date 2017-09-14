import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {NgbModal,ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./home-page.component.css'],
})
export class HomePageComponent implements OnInit {

  draftTaskList: any[];
  pendingTaskList=[];


  //open new task popup
  onNewTask(id: string) {
    this.modalService.open(id, { windowClass: 'task-modal' });
    console.log("found");
  }
  onAddTask(){
    //todo add task to database
  }
  onTaskDrop(e: any,id:string) {
    // Get the dropped data here
    this.pendingTaskList.push(e.dragData);
    //todo change task status
    this.onRemoveTask(e.dragData, this.draftTaskList);
    this.modalService.open(id, { windowClass: 'recommend-modal' });
  }
  onRemoveTask(task: any, list: Array<any>) {
    let index = list.map(function (e) {
      return e.taskName
    }).indexOf(task.taskName);
    list.splice(index, 1);
  }
  onSendInvitation(){
    //todo  send invitation
  }

  constructor(private modalService: NgbModal) {

    //added dummy tasklist here
    //todo get task details from database
    this.draftTaskList = [{
      taskName: "create manager page",
      deadLine: "12/09/2017",
      description: "create a web page using angular, hmtl, css,etc",
      status: "",
      member: ""
    },
      {
        taskName: "create home page",
        deadLine: "15/09/2017",
        description: "create a web page using angular, hmtl, css,etc",
        status: "",
        member: ""
      },
      {
        taskName: "create login page",
        deadLine: "17/09/2017",
        description: "create a web page using angular, hmtl, css,etc",
        status: "",
        member: ""
      }];
  }

  ngOnInit() {

  }
}
