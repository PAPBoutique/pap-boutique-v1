import { Component, ViewChild, ElementRef } from '@angular/core';

@Component({
  selector: 'app-buy-product',
  templateUrl: './buy-product.component.html',
  styleUrls: ['./buy-product.component.css']
})
export class BuyProductComponent{

  
  months: number[] = [];

  ngOnInit() {
    this.selectedMonths();
    this.selectedYears();
  }
  

  updateCardNumberBox(cardNumber: string) {
    const numericRegex = /^\d*$/;
  
    const cardNumberInput = document.querySelector('.card-number-input') as HTMLInputElement;
    const cardNumberBox = document.querySelector('.card-number-box') as HTMLElement;
  
    if (cardNumberInput && cardNumberBox) {
      if (!numericRegex.test(cardNumber)) {
        const sanitizedCardNumber = cardNumber.replace(/\D/g, '');
        cardNumberInput.value = sanitizedCardNumber;
      } else {
        cardNumberInput.value = cardNumber;
      }
  
      cardNumberBox.textContent = cardNumberInput.value;
    }
  }
  

updateCardHolderBox(cardHolderName: string) {
  const alphabeticRegex = /^[A-Za-z\s]+$/;

  const cardHolderInput = document.querySelector('.card-holder-input') as HTMLInputElement;
  const cardHolderBox = document.querySelector('.card-holder-name') as HTMLElement;

  if (cardHolderInput && cardHolderBox) {
    if (!alphabeticRegex.test(cardHolderName)) {
      const sanitizedCardHolderName = cardHolderName.replace(/[^A-Za-z\s]/g, '');
      cardHolderInput.value = sanitizedCardHolderName;
    } else {
      cardHolderInput.value = cardHolderName;
    }

    cardHolderBox.textContent = cardHolderInput.value;
  }
}

///////////////////////////////////// 
  updateExpirationMonthBox(monthValue : string) {
    const expMonthElement = document.querySelector('.exp-month') as HTMLElement;
    if (expMonthElement) {
      expMonthElement.innerText = monthValue + ' / ';
    }
  }

  showCardLogo()
  {const cardNumber =  document.querySelector('.card-number-input')  as HTMLInputElement
  const selectedCardNumber = cardNumber.value

  const cardLogo = document.querySelector('.front .image img:nth-child(2)') as HTMLImageElement;
  const cardFront = document.querySelector('.front') as HTMLDivElement;
  const cardBack = document.querySelector('.back') as HTMLDivElement;

  
  if (selectedCardNumber.startsWith('4')) {
      cardLogo.style.display = 'block'; 
      cardLogo.alt = 'Visa';
      cardLogo.src = '../../../../assets/img/visa.png';
      cardFront.style.backgroundColor = '#0D6EFD';
      cardBack.style.backgroundColor = '#F5B226';

  } else if (selectedCardNumber.startsWith('5')) {
      cardLogo.style.display = 'block'; 
      cardLogo.alt = 'Mastercard';
      cardLogo.src = '../../../../assets/img/mastercard.png';
      cardFront.style.backgroundColor = '#F79E1B';
      cardBack.style.backgroundColor = '#EB001B';

  } 
  else if (selectedCardNumber.startsWith('6')) {
      cardLogo.style.display = 'block';
      cardLogo.style.height = '50px'
      cardLogo.alt = 'Dsicover';
      cardLogo.src = '../../../../assets/img/img.icons8.png';
      cardFront.style.backgroundColor = '#F76C00';
      cardBack.style.backgroundColor = '#D8D8D9';

  }
  else {
      cardLogo.style.display = 'none';
      cardLogo.src =''
      cardLogo.alt = '';
  }
  }

  updateExpirationYearBox(yearValue : string)
  {
    const expYearElement = document.querySelector('.exp-year') as HTMLElement
    if(expYearElement)
    expYearElement.innerHTML = yearValue

  }

  selectedYears() {
    const yearSelect = document.querySelector('.year-input') as HTMLSelectElement;
    const currentYear = new Date().getFullYear();
  
    for (let i = currentYear; i <= currentYear + 10; i++) {
      const option = document.createElement('option');
      option.value = i.toString();
      option.textContent = i.toString();
      yearSelect.appendChild(option);
    }
  } 


  selectedMonths()
  {
    const monthSelect = document.querySelector('.month-input');

    for (let i = 1; i <= 12; i++) {
      const option = document.createElement('option');
      option.value = ('0' + i).slice(-2); 
      option.textContent = ('0' + i).slice(-2);
      if(monthSelect)
      monthSelect.appendChild(option);
  }
  }

  rotateCardBack()
  {
   const cardFront = document.querySelector('.front') as HTMLDivElement;
   cardFront.style.transform = 'perspective(1000px) rotateY(-180deg)';
   const cardBack = document.querySelector('.back') as HTMLDivElement;
   cardBack.style.transform = 'perspective(1000px) rotateY(0deg)';

  }

  rotateCardToFront()
  {
       const cardFront = document.querySelector('.front') as HTMLDivElement;
       cardFront.style.transform = 'perspective(1000px) rotateY(0deg)';
       const cardBack =  document.querySelector('.back') as HTMLDivElement;
       cardBack.style.transform = 'perspective(1000px) rotateY(180deg)';
  }

  updateCvvBox(cvvValue : string)
  {

    const numericRegex = /^\d*$/;
  
    const cvvValueInput = document.querySelector('.cvv-input') as HTMLInputElement;
    const cvvNumberBox = document.querySelector('.cvv-box') as HTMLElement;
  
    if (cvvValueInput && cvvNumberBox) {
      if (!numericRegex.test(cvvValue)) {
        const sanitizedCardNumber = cvvValue.replace(/\D/g, '');
        cvvValueInput.value = sanitizedCardNumber;
      } else {
        cvvValueInput.value = cvvValue;
      }
  
      cvvNumberBox.textContent = cvvValueInput.value;
    }

  }

}