import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MtnDealerReportTestModule } from '../../../test.module';
import { ReportParameterDetailComponent } from 'app/entities/report-parameter/report-parameter-detail.component';
import { ReportParameter } from 'app/shared/model/report-parameter.model';

describe('Component Tests', () => {
  describe('ReportParameter Management Detail Component', () => {
    let comp: ReportParameterDetailComponent;
    let fixture: ComponentFixture<ReportParameterDetailComponent>;
    const route = ({ data: of({ reportParameter: new ReportParameter(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MtnDealerReportTestModule],
        declarations: [ReportParameterDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ReportParameterDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ReportParameterDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load reportParameter on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.reportParameter).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
