import { TestBed } from '@angular/core/testing';

import { SodaMachineService } from './soda-machine.service';

describe('SodaMachineService', () => {
  let service: SodaMachineService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SodaMachineService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
