import { OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { PageEvent } from '@angular/material/paginator';
import { Generic } from '../models/generic';
import { CommonService } from '../services/common.service';

export abstract class CommonListarComponent<E extends Generic, S extends CommonService<E>> implements OnInit {

  titulo: string
  entities: E[] = [];
  protected nombreModel: string;
  totalRegistros: number = 0;
  sizePagina: number = 5;
  paginaActual: number = 0;

  constructor(protected service: S) { }

  ngOnInit(): void {
    this.getPagina();
  }

  public paginar(event: PageEvent): void {
    this.paginaActual = event.pageIndex;
    this.sizePagina = event.pageSize;
    this.getPagina();
  }

  public getPagina(): void {

    this.service.listarPaginas(this.paginaActual.toString(), this.sizePagina.toString())
      .subscribe( p => {
        this.entities = p.content as E[];
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
        this.service.eliminar(id)
          .subscribe(
            _ => {
              Swal.fire(`${this.nombreModel} eliminado`, `${this.nombreModel} eliminado con éxito`, 'success');
              this.getPagina();
            },
            error => {
              Swal.fire(`${this.nombreModel} no eliminado`, `${this.nombreModel} no ha podido ser eliminado`, 'error');
              console.log(error);
            })
      } else{
        Swal.fire(`${this.nombreModel} no eliminado`);
      }
    })
    
  }
}
