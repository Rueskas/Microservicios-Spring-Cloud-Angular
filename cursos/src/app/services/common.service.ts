import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Generic } from '../models/generic';

export abstract class CommonService<T extends Generic> {
  protected endpoint: string;
  protected url: string = 'http://localhost:8090/api';
  protected headers = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(protected http: HttpClient) { }
 
  public listar() : Observable<T[]> {
    return this.http.get<T[]>(this.url+this.endpoint);
  }

  public listarPaginas(page: string, size: string) : Observable<any> {
    const params = new HttpParams()
      .set('page',page)
      .set('size', size);
      console.log(page + ' ' + size + ' '+ this.endpoint + ' ');
      console.log(this.http);
    return this.http.get<any>(`${this.url+this.endpoint}/page`, {params: params});
  }

  public ver(id: number) : Observable<T> {
    return this.http.get<T>(`${this.url+this.endpoint}/${id}`);
  }

  public crear(entity: T) : Observable<T> {
    return this.http.post<T>(`${this.url+this.endpoint}`, entity, {headers: this.headers});
  }

  public editar(entity: T) : Observable<T> {
    return this.http.put<T>(`${this.url+this.endpoint}/${entity.id}`, entity, {headers: this.headers});
  }
  
  public eliminar(id: number) : Observable<void> {
    return this.http.delete<void>(`${this.url+this.endpoint}/${id}`);
  }
}
