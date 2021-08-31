using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using FIT5032_HotelBooking.Models;

namespace FIT5032_HotelBooking.Controllers
{
    public class HotelsController : Controller
    {
        private ModelContainer db = new ModelContainer();

        // GET: Hotels
        public ActionResult Index()
        {
            return View(db.Hotel.ToList());
        }

        // GET: Hotels/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Hotel hotel = db.Hotel.Find(id);
            if (hotel == null)
            {
                return HttpNotFound();
            }
            return View(hotel);
        }

        [Authorize(Roles = "Staff")]
        // GET: Hotels/Create
        public ActionResult Create()
        {
            return View();
        }

        [Authorize(Roles = "Staff")]
        // POST: Hotels/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "Id,Name,Description,Email,Latitude,Longitude,Price,Address,RatingClass,NoR,Capacity")] Hotel hotel)
        {
            if (ModelState.IsValid)
            {
                db.Hotel.Add(hotel);
                db.SaveChanges();
                ViewBag.Success = "Create Successfully!";
                return View(hotel);
            }

            return View(hotel);
        }

        [Authorize(Roles = "Staff")]
        // GET: Hotels/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Hotel hotel = db.Hotel.Find(id);
            if (hotel == null)
            {
                return HttpNotFound();
            }
            return View(hotel);
        }

        [Authorize(Roles = "Staff")]
        // POST: Hotels/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "Id,Name,Description,Email,Latitude,Longitude,Price,Address,RatingClass,NoR,Capacity")] Hotel hotel)
        {
            if (ModelState.IsValid)
            {
                db.Entry(hotel).State = EntityState.Modified;
                db.SaveChanges();
                ViewBag.Success = "Edit Successfully!";

                return View(hotel);
            }
            return View(hotel);
        }

        [Authorize(Roles = "Staff")]
        // GET: Hotels/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Hotel hotel = db.Hotel.Find(id);
            if (hotel == null)
            {
                return HttpNotFound();
            }
            return View(hotel);
        }

        [Authorize(Roles = "Staff")]
        // POST: Hotels/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            Hotel hotel = db.Hotel.Find(id);
            db.Hotel.Remove(hotel);
            db.SaveChanges();
            ViewBag.Success = "Delete Successfully!";

            return View(hotel);
        }


        //GET: Hotel_Data_/Map
        public ActionResult Map(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Hotel hotel_Data = db.Hotel.Find(id);
            if (hotel_Data == null)
            {
                return HttpNotFound();
            }
            return View(hotel_Data);
        }

        // GET: Hotels
        public ActionResult Navigation()
        {
            return View();
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }
    }
}
