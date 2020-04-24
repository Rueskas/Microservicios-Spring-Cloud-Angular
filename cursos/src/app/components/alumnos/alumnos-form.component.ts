import { Component, OnInit } from '@angular/core';
import { AlumnoService } from 'src/app/services/alumno.service';
import { Alumno } from 'src/app/models/alumno';
import { Router, ActivatedRoute } from '@angular/router'
import Swal from 'sweetalert2';

@Component({
  selector: 'app-alumnos-form',
  templateUrl: './alumnos-form.component.html',
  styleUrls: ['./alumnos-form.component.css']
})
export class AlumnosFormComponent implements OnInit {
  titulo: string = "Crear Alumno";
  alumno: Alumno = new Alumno();
  errors: any;
  constructor( 
    private _alumnoService: AlumnoService,
    private _router: Router,
    private _activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this._activatedRoute.paramMap.subscribe(
      params => {
        if(params.get('id')){
          this._alumnoService.ver(+params.get('id')).subscribe(
            alumno => this.alumno = alumno,
            error => {
              if(error.status === 404){
                Swal.fire('Alumno No Encontrado', `El alumno con ID: ${params.get('id')} no existe`, 'error');
                this._router.navigate(['/alumnos']);
              }
            }
          )
        }
      }
    )
  }

  public crear(): void{
    this._alumnoService.crear(this.alumno)
    .subscribe(
      alumno => {
        Swal.fire('Alumno Creado', `Alumno ${alumno.nombre} creado con éxito`, 'success');
        this._router.navigate(['/alumnos']);
     },
     error => {
       if(error.status === 400) {
        this.errors = error.error; 
        console.log(this.errors);
       }
     });
  }

  public editar(): void{
    this._alumnoService.editar(this.alumno)
    .subscribe(
      alumno => {
        Swal.fire('Alumno Editado', `Alumno ${alumno.nombre} editado con éxito`, 'success');
        this._router.navigate(['/alumnos']);
     },
     error => {
       if(error.status === 400) {
        this.errors = error.error; 
        console.log(this.errors);
       }
     });
  }

}
