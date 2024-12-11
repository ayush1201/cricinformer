import { Component, OnInit } from '@angular/core';
import { ApiCallService } from '../../services/api-call.service';
import { CommonModule } from '@angular/common';
import { MatchCardComponent } from '../../components/match-card/match-card.component';
import { NgxTypedJsModule } from 'ngx-typed-js';


@Component({
  selector: 'app-live',
  standalone: true,
  imports: [CommonModule,MatchCardComponent,NgxTypedJsModule],
  templateUrl: './live.component.html',
  styleUrl: './live.component.css'
})
export class LiveComponent implements OnInit {
  constructor(private _api: ApiCallService) {

  }
 
  liveData: any;
  loading = false
  
  ngOnInit(): void {
    this.loadScore()
  }

  loadScore() {
    this.loading = true
    
    this._api.getLiveMatches().subscribe({
      next: data => {
        this.liveData = data;
        
        // console.log("hello")
        console.log(this.liveData);    
        this.loading = false
      },
      error: (error) => {
        console.log(error)
        this.loading = false
      }
    })

  }

  refreshScore() {

    
    this.loadScore()
  }



}
