import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TargetInfoComponent } from './target-info.component';

describe('TargetInfoComponent', () => {
  let component: TargetInfoComponent;
  let fixture: ComponentFixture<TargetInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TargetInfoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TargetInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
