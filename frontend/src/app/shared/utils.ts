export const removeAccents = (value: string): string => {
  value = value.replace(/[ÀÁÂÃ]/, 'A');
  value = value.replace(/[àáâã]/, 'a');
  value = value.replace(/[ÈÉÊË]/, 'E');
  value = value.replace(/[èéê]/, 'e');
  value = value.replace(/[ÌÍ]/, 'I');
  value = value.replace(/[ìí]/, 'i');
  value = value.replace(/[ÒÓ]/, 'O');
  value = value.replace(/[òó]/, 'o');
  value = value.replace(/[ÙÚ]/, 'U');
  value = value.replace(/[ùú]/, 'u');
  value = value.replace(/[Ç]/, 'C');
  value = value.replace(/[ç]/, 'c');

  return value.toLowerCase();
};