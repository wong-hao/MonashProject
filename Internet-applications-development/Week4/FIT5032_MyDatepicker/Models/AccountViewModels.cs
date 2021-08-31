using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace FIT5032_MyDatepicker.Models
{
    public class AccountViewModels
    {
        public int Id { get; set; }
        public String Name { get; set; }

        [Display(Name = "Date of Birth")]
        public DateTime DOB { get; set; }
    }
}