const colors = {
  red: 'red'
};

  const formatDate = (param) => {
    const date = new Date(param);
    const [year, month, day] = [
      date.getFullYear(),
      date.getMonth() + 1,
      date.getDate(),
      date.getHours(),
      date.getMinutes(),
    ];

    return `${`0${day}`.slice(-2)}-${`0${month}`.slice(-2)}-${year}`;
  };

const formatAmount = (x) => x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');

const removeCommas = (amount) => {
  if (/(,)/.test(amount) === true) {
    return amount.replace(/,/g, '');
  }
  return amount;
};

const styles = {
  tableDropdown: { position: 'absolute' }
};

export { colors, formatDate, formatAmount, removeCommas, styles };
