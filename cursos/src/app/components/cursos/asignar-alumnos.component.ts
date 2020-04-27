import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { CursoService } from 'src/app/services/curso.service';
import { AlumnoService } from 'src/app/services/alumno.service';
import { Curso } from 'src/app/models/curso';
import Swal from 'sweetalert2';
import { Alumno } from 'src/app/models/alumno';
import { SelectionModel } from '@angular/cdk/collections';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-asignar-alumnos',
  templateUrl: './asignar-alumnos.component.html',
  styleUrls: ['./asignar-alumnos.component.css']
})
export class AsignarAlumnosComponent implements OnInit {
  
  curso: Curso = new Curso();
  alumnosAsignar: Alumno[] = [];
  mostrarColumnas: String[] = ['Nombre', 'Apellidos','Seleccionar'];
  mostrarColumnasAlumnos: String[] = ['ID', 'Nombre', 'Apellidos','Email', 'Fecha', 'Eliminar'];
  term: string = '';
  tabIndex: number = 1;
  
  datasource: MatTableDataSource<Alumno>;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  seleccion: SelectionModel<Alumno> = new SelectionModel<Alumno>(true, []);
  
  constructor(
    private _router: Router,
    private _activatedRoute: ActivatedRoute,
    private _cursoService: CursoService,
    private _alumnoService: AlumnoService
  ) { }

  ngOnInit(): void {
    this.refrescar();
  }

  public filtrar() : void{
    if(this.term.trim() != ''){
      this._alumnoService.filtrar(this.term).subscribe(
        result => {
          const alumnoIdsAsignados = this.curso.alumnos.map(a => a.id);
          this.alumnosAsignar = result.filter(a => alumnoIdsAsignados.indexOf(a.id) == -1);
        }, 
        error => {
          if(error.status == 404){
            this.alumnosAsignar = [];
          }
        }
      );
    } else {
      this.alumnosAsignar = [];
    }
  }

  public seleccionarTodos(): void {
    this.todosSeleccionados()?
      this.seleccion.clear() : this.alumnosAsignar.forEach(a => this.seleccion.select(a));
  }

  public todosSeleccionados(): boolean {
    return this.alumnosAsignar.length == this.seleccion.selected.length;
  }

  public asignar(): void {
    this._cursoService.asignarAlumnos(this.curso, this.seleccion.selected).subscribe(
      result => {
        Swal.fire(
          'Alumnos Asignados', 
        `Los alumnos se han asignado al curso ${this.curso.nombre} correctamente`, 
        'success');
        this.tabIndex = 2;
        this.refrescar();
      },
      error => {
        console.log(error);
        if(error.status == 500){
          Swal.fire('Cuidado', 'Un alumno seleccionado ya esta asociado a otro curso.', 'error');
        }
      }
    );
  }

  public refrescar(): void {
    this.seleccion.clear();
    this.term = '';
    this.alumnosAsignar = [];
    this.cargarCurso();
  }

  public cargarCurso(): void {
    this._activatedRoute.paramMap.subscribe(
      params => {
        const id = params.get('id');
          this._cursoService.ver(+id).subscribe(
            curso => {
              this.curso = curso;
              this.datasource = new MatTableDataSource(this.curso.alumnos);
              this.datasource.paginator = this.paginator;
              this.paginator._intl.itemsPerPageLabel= "Registros por página";
            },
            error => {
              if(error.status == 404) {
                Swal.fire('Error', 'Curso no encontrado.', 'error');
              } else{
                Swal.fire('Error', 'Ha ocurrido un error inesperado.', 'error');
              }
              this._router.navigate(['/cursos']);
            }
          )
      }
    )
  }

  public eliminarAlumno(alumno: Alumno) : void {
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
        this._cursoService.eliminarAlumno(this.curso, alumno).subscribe(
          _ => {
            Swal.fire(
              'Eliminado del curso',
              `El alumno ${alumno.nombre} se ha eliminado del curso ${this.curso.nombre}.`,
              'success');
            this.refrescar();
          }
        )
      } else{
        Swal.fire(`Alumno no eliminado del curso.`);
      }
    })
    

  }
}