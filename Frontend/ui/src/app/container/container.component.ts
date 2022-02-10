import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { SodaMachineService } from '../soda-machine.service';

@Component({
  selector: 'app-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css'],
})
export class ContainerComponent implements OnInit {
  constructor(private sodaMachineService: SodaMachineService) {}
  status = '';
  remainingSoda = 0;

  ngOnInit(): void {
    this.getRemainingSoda();
  }

  getRemainingSoda = () => {
    this.status = '';
    this.sodaMachineService.getRemainingSoda().subscribe((data) => {
      this.remainingSoda = data.quantity;
    });
  };

  onInsert = () => {
    if (this.remainingSoda === 0) {
      alert('No Soda available');
      return;
    }

    this.sodaMachineService.insert().subscribe(
      (data) => {
        this.getRemainingSoda();
        this.status = 'INSERTED';
      },
      () => {
        this.getRemainingSoda();
        this.status = 'INSERTED';
      }
    );
  };

  onWithDraw = () => {
    this.status = 'WITHDRAW';
    this.sodaMachineService.withdraw().subscribe(
      (data) => {
        this.getRemainingSoda();
        this.status = '';
      },
      () => {
        this.getRemainingSoda();
        this.status = '';
      }
    );
  };

  onStop = () => {
    this.status = 'STOP';
    this.sodaMachineService.cancel().subscribe(
      (data) => {
        this.getRemainingSoda();
        this.status = '';
      },
      () => {
        this.getRemainingSoda();
        this.status = '';
      }
    );
  };

  isInsertEnable = () => {
    return this.status === '';
  };
}
