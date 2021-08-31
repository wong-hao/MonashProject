using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace FIT5032_MyCodeFirst.Models
{
    public class FIT5032_MyCodeFirstContainer : DbContext
    {
        public DbSet<Student> Students { get; set; }
        public DbSet<Unit> Units { get; set; }

        public FIT5032_MyCodeFirstContainer()
            : base("FIT5032_MyCodeFirst")
        {
        }

        public static FIT5032_MyCodeFirstContainer Create()
        {
            return new FIT5032_MyCodeFirstContainer();
        }
    }
}