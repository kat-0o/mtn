import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MtnDealerReportTestModule } from '../../../test.module';
import { ReportParameterComponent } from 'app/entities/report-parameter/report-parameter.component';
import { ReportParameterService } from 'app/entities/report-parameter/report-parameter.service';
import { ReportParameter } from 'app/shared/model/report-parameter.model';

describe('Component Tests', () => {
  describe('ReportParameter Management Component', () => {
    let comp: ReportParameterComponent;
    let fixture: ComponentFixture<ReportParameterComponent>;
    let service: ReportParameterService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MtnDealerReportTestModule],
        declarations: [ReportParameterComponent],
      })
        .overrideTemplate(ReportParameterComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ReportParameterComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ReportParameterService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ReportParameter(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.reportParameters && comp.reportParameters[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
