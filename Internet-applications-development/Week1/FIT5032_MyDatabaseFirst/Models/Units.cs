//------------------------------------------------------------------------------
// <auto-generated>
//     此代码已从模板生成。
//
//     手动更改此文件可能导致应用程序出现意外的行为。
//     如果重新生成代码，将覆盖对此文件的手动更改。
// </auto-generated>
//------------------------------------------------------------------------------

namespace FIT5032_MyDatabaseFirst.Models
{
    using System;
    using System.Collections.Generic;
    
    public partial class Units
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
        public int StudentId { get; set; }
    
        public virtual Students Students { get; set; }
    }
}
