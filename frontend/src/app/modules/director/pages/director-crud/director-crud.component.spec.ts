import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DirectorCrudComponent } from './director-crud.component';

describe('ListComponent', () => {
  let component: DirectorCrudComponent;
  let fixture: ComponentFixture<DirectorCrudComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DirectorCrudComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DirectorCrudComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
