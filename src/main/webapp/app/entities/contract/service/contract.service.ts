import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IContract, getContractIdentifier } from '../contract.model';

export type EntityResponseType = HttpResponse<IContract>;
export type EntityArrayResponseType = HttpResponse<IContract[]>;

@Injectable({ providedIn: 'root' })
export class ContractService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/contracts');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(contract: IContract): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(contract);
    return this.http
      .post<IContract>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(contract: IContract): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(contract);
    return this.http
      .put<IContract>(`${this.resourceUrl}/${getContractIdentifier(contract) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(contract: IContract): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(contract);
    return this.http
      .patch<IContract>(`${this.resourceUrl}/${getContractIdentifier(contract) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IContract>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IContract[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addContractToCollectionIfMissing(contractCollection: IContract[], ...contractsToCheck: (IContract | null | undefined)[]): IContract[] {
    const contracts: IContract[] = contractsToCheck.filter(isPresent);
    if (contracts.length > 0) {
      const contractCollectionIdentifiers = contractCollection.map(contractItem => getContractIdentifier(contractItem)!);
      const contractsToAdd = contracts.filter(contractItem => {
        const contractIdentifier = getContractIdentifier(contractItem);
        if (contractIdentifier == null || contractCollectionIdentifiers.includes(contractIdentifier)) {
          return false;
        }
        contractCollectionIdentifiers.push(contractIdentifier);
        return true;
      });
      return [...contractsToAdd, ...contractCollection];
    }
    return contractCollection;
  }

  protected convertDateFromClient(contract: IContract): IContract {
    return Object.assign({}, contract, {
      dataInit: contract.dataInit?.isValid() ? contract.dataInit.format(DATE_FORMAT) : undefined,
      dataFinal: contract.dataFinal?.isValid() ? contract.dataFinal.format(DATE_FORMAT) : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dataInit = res.body.dataInit ? dayjs(res.body.dataInit) : undefined;
      res.body.dataFinal = res.body.dataFinal ? dayjs(res.body.dataFinal) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((contract: IContract) => {
        contract.dataInit = contract.dataInit ? dayjs(contract.dataInit) : undefined;
        contract.dataFinal = contract.dataFinal ? dayjs(contract.dataFinal) : undefined;
      });
    }
    return res;
  }
}
