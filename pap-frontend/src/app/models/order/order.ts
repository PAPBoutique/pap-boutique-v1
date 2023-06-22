export type Order = {
    id?:number,
    product : string,
    quantity : number ,
    creationDate : string,
    price : number ,
    client : string,
    status : string,
    checked:boolean
}