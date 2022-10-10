import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddTargetComponent } from './add-target.component';

describe('AddTargetComponent', () => {
  let component: AddTargetComponent;
  let fixture: ComponentFixture<AddTargetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddTargetComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddTargetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
