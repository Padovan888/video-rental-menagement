import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TitleCrudComponent } from './title-crud.component';

describe('TitleCrudComponent', () => {
  let component: TitleCrudComponent;
  let fixture: ComponentFixture<TitleCrudComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TitleCrudComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TitleCrudComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
