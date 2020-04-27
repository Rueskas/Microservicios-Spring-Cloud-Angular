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
  private fotoSeleccionada: File;
  protected nombreModel: string = "Alumno";
  protected rutaRedirect: string = "/alumnos";

  constructor(
    protected service: AlumnoService,
    protected router: Router,
    protected activatedRoute: ActivatedRoute) {
    super(service, router, activatedRoute);
  }

  public seleccionarFoto(event): void {
    this.fotoSeleccionada = event.target.files[0];
    if(this.fotoSeleccionada.type.indexOf('image') < 0){
      Swal.fire('Error', 'La foto debe ser una imagen.', 'error');
      this.fotoSeleccionada = null;
    }
  }


  public crear(): void {
    if (!this.fotoSeleccionada) {
      super.crear();
    } else {
      this.service.crearConFoto(this.entity, this.fotoSeleccionada)
        .subscribe(
          entity => {
            Swal.fire(`${this.nombreModel} Creado`, `${this.nombreModel} ${this.entity.nombre} creado con éxito`, 'success');
            this.router.navigate([this.rutaRedirect]);
          },
          error => {
            if (error.status === 400) {
              this.errors = error.error;
              console.log(this.errors);
            }
          });
    }

  }

  public editar(): void {
    if (!this.fotoSeleccionada) {
      super.editar();
    } else {
      this.service.editarConFoto(this.entity, this.fotoSeleccionada)
        .subscribe(
          entity => {
            Swal.fire(`${this.nombreModel} Editado`, `${this.nombreModel} ${this.entity.nombre} editado con éxito`, 'success');

            this.router.navigate(['/alumnos']);
          },
          error => {
            if (error.status === 400) {
              this.errors = error.error;
              console.log(this.errors);
            }
          });
    }
  }
}
