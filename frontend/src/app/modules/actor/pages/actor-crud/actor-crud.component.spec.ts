import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActorCrudComponent } from './actor-crud.component';

describe('ListComponent', () => {
  let component: ActorCrudComponent;
  let fixture: ComponentFixture<ActorCrudComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ActorCrudComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ActorCrudComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
