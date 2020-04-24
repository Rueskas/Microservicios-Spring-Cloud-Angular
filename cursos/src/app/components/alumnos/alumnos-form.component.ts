import { Component, OnInit } from '@angular/core';
import { AlumnoService } from 'src/app/services/alumno.service';
import { Alumno } from 'src/app/models/alumno';
import { Router, ActivatedRoute } from '@angular/router'
import Swal from 'sweetalert2';
import { CommonFormComponent } from '../common-form';

@Component({
  selector: 'app-alumnos-form',
  templateUrl: './alumnos-form.component.html',
  styleUrls: ['./alumnos-form.component.css']
})
export class AlumnosFormComponent extends CommonFormComponent<Alumno, AlumnoService> implements OnInit {
  entity: Alumno = new Alumno();
  protected nombreModel: string = "Alumno";
  protected rutaRedirect: string = "/alumnos";
  constructor( 
    protected service: AlumnoService,
    protected router: Router,
    protected activatedRoute: ActivatedRoute) {
      super(service, router, activatedRoute);
     }


}
