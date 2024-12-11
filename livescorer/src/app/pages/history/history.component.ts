import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatchCardComponent } from '../../components/match-card/match-card.component';
import { ApiCallService } from '../../services/api-call.service';
import { NgxTypedJsModule } from 'ngx-typed-js';

@Component({
  selector: 'app-history',
  standalone: true,
  imports: [NgxTypedJsModule,MatchCardComponent],
  templateUrl: './history.component.html',
  styleUrl: './history.component.css'
})
export class HistoryComponent implements OnInit {
  loading=false
  allMatches: any;

  constructor(private _api:ApiCallService){

  }
  ngOnInit(): void {
   this.loadMatchHistory()
  }
  loadMatchHistory(){

    this._api.getAllMatches()
    .subscribe({
      next:data=>{
        this.allMatches=data;
      }
    })

  }


}
