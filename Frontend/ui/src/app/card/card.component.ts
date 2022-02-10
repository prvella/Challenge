import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css'],
})
export class CardComponent implements OnInit {
  @Input() title = '';
  @Input() description = '';
  @Input() action = '';
  @Input() cardClick;
  constructor() {}

  ngOnInit(): void {}

  onHandleClick = () => {
    this.cardClick();
  };
}
