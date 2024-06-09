import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FreeagentComponent } from './freeagent.component';

describe('FreeagentComponent', () => {
  let component: FreeagentComponent;
  let fixture: ComponentFixture<FreeagentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FreeagentComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FreeagentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
