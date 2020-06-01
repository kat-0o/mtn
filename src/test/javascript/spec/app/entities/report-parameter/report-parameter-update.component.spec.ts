import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MtnDealerReportTestModule } from '../../../test.module';
import { ReportParameterUpdateComponent } from 'app/entities/report-parameter/report-parameter-update.component';
import { ReportParameterService } from 'app/entities/report-parameter/report-parameter.service';
import { ReportParameter } from 'app/shared/model/report-parameter.model';

describe('Component Tests', () => {
  describe('ReportParameter Management Update Component', () => {
    let comp: ReportParameterUpdateComponent;
    let fixture: ComponentFixture<ReportParameterUpdateComponent>;
    let service: ReportParameterService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MtnDealerReportTestModule],
        declarations: [ReportParameterUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ReportParameterUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ReportParameterUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ReportParameterService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ReportParameter(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new ReportParameter();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
