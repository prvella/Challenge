import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  @Input() status;
  @Input() remainingSoda;
  constructor() {}

  ngOnInit(): void {}

  getStatus = () => {
    if (this.status === 'INSERTED') return 'Coin Inserted';
    if (this.status === 'START') return 'Soda Withdraw';
    if (this.status === 'STOP') return 'Canceled';
    return '';
  };
}
