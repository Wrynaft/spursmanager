import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InjuryComponent } from './injury.component';

describe('InjuryComponent', () => {
  let component: InjuryComponent;
  let fixture: ComponentFixture<InjuryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InjuryComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(InjuryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
