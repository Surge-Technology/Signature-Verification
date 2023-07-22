import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EchannelComponent } from './signatureVerification.component';

describe('EchannelComponent', () => {
  let component: EchannelComponent;
  let fixture: ComponentFixture<EchannelComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EchannelComponent]
    });
    fixture = TestBed.createComponent(EchannelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
