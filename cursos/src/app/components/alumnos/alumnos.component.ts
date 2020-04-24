import { Component, OnInit } from '@angular/core';
import { AlumnoService } from 'src/app/services/alumno.service';
import { Alumno } from 'src/app/models/alumno';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-alumnos',
  templateUrl: './alumnos.component.html',
  styleUrls: ['./alumnos.component.css']
})
export class AlumnosComponent implements OnInit {

  titulo: string = 'Listado de Alumnos';
  alumnos: Alumno[] = [];
  constructor(private _alumnoService: AlumnoService) { }

  ngOnInit(): void {
    this._alumnoService.listar()
      .subscribe( alumnos => this.alumnos = alumnos);
  }

  public eliminar(id: number){
    Swal.fire({
      title: '¿Estás seguro?',
      text: 'Una vez eliminado no se podrá recuperar',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Eliminar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.value) {
        this._alumnoService.eliminar(id)
          .subscribe(
            _ => {
              Swal.fire('Alumno eliminado', 'Alumno eliminado con éxito', 'success');
              this.alumnos = this.alumnos.filter(a => a.id != id);
            },
            error => {
              Swal.fire('Alumno no eliminado', 'El Alumno no ha podido ser eliminado.', 'error');
              console.log(error);
            }
            )
      } else{
        Swal.fire('Alumno NO eliminado');
      }
    })
    
  }
}
