import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiCallService } from '../../services/api-call.service';

@Component({
  selector: 'app-point-table',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './point-table.component.html',
  styleUrl: './point-table.component.css'
})
export class PointTableComponent implements OnInit {
  pointTable: any;
  pointTableRow:any

  constructor(private _api: ApiCallService) {}
  ngOnInit(): void {
    this.loadTable();
  }
  loadTable() {
    this._api.getPointTable().subscribe({
      next: (data) => {
        this.pointTable = data;
        console.log(this.pointTable);        
        this.pointTableRow=[...this.pointTable[0]]
       
        this.pointTable=this.pointTable.filter((item:any,index:any)=>index>0)
       
        
        
       
        
      },
    });
  }
}
