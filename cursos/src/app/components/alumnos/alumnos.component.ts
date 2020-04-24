import { Component, OnInit, ViewChild } from '@angular/core';
import { AlumnoService } from 'src/app/services/alumno.service';
import { Alumno } from 'src/app/models/alumno';
import Swal from 'sweetalert2';
import { PageEvent, MatPaginator, MatPaginatorIntl } from '@angular/material/paginator';

@Component({
  selector: 'app-alumnos',
  templateUrl: './alumnos.component.html',
  styleUrls: ['./alumnos.component.css']
})
export class AlumnosComponent implements OnInit {

  titulo: string = 'Listado de Alumnos';
  alumnos: Alumno[] = [];
  totalRegistros: number = 0;
  sizePagina: number = 5;
  paginaActual: number = 0;

  constructor(private _alumnoService: AlumnoService) { }

  ngOnInit(): void {
    this.getPagina();
  }

  public paginar(event: PageEvent): void {
    this.paginaActual = event.pageIndex;
    this.sizePagina = event.pageSize;
    this.getPagina();
  }

  public getPagina(): void {

    this._alumnoService.listarPaginas(this.paginaActual.toString(), this.sizePagina.toString())
      .subscribe( p => {
        this.alumnos = p.content as Alumno[];
        this.totalRegistros = p.totalElements as number;
      });
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
              this.getPagina();
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
