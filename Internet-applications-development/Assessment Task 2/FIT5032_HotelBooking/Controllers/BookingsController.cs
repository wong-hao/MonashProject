using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using FIT5032_HotelBooking.Models;
using Microsoft.AspNet.Identity;

namespace FIT5032_HotelBooking.Controllers
{
    [Authorize]
    public class BookingsController : Controller
    {
        private ModelContainer db = new ModelContainer();

        // GET: Bookings
        public ActionResult Index()
        {
            try
            {

                var userId = User.Identity.GetUserId();

                var bookings = db.Booking.Where(s => s.UserId == userId).ToList();
                return View(bookings);

            }
            catch
            {

                var userId = User.Identity.GetUserId();

                var bookings = db.Booking.Where(s => s.UserId == userId).ToList();
                return View(bookings);

            }
        }

        // GET: Bookings/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Booking booking = db.Booking.Find(id);
            if (booking == null)
            {
                return HttpNotFound();
            }
            return View(booking);
        }

        // GET: Bookings/Create
        public ActionResult Create()
        {
            var model = new Booking()
            {
                List = db.Hotel.Select(p => new SelectListItem { Text = p.Name, Value = p.Id.ToString() }).ToList()
            };
            return View(model);
        }

        // POST: Bookings/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "Id,StartDate,EndDate,BookingNumber,HotelId,UserId")] Booking booking)
        {
            if (ModelState.IsValid)
            {
                var userId = User.Identity.GetUserId();


                if (db.Booking.Where(s => s.EndDate >= booking.StartDate && s.StartDate <= booking.EndDate&& s.UserId==userId).Any())
                {
                    var model = new Booking()
                    {
                        List = db.Hotel.Select(p => new SelectListItem { Text = p.Name, Value = p.Id.ToString() }).ToList()
                    };
                    ViewBag.Failure = "Booking Constraint: There is already a booking for that date. Please choose a different date.";
                    return View(model);
                }

                Booking bk = new Booking();
                bk.HotelId = booking.HotelId;
                bk.BookingNumber = (int)booking.BookingNumber;
                bk.StartDate = (System.DateTime)booking.StartDate;
                bk.EndDate = (System.DateTime)booking.EndDate;
                bk.UserId = userId; //booking.user_id;

                //Getting the hotel
                Hotel hotel = db.Hotel.Find(booking.HotelId);

                var newHotelCapacity = hotel.Capacity - booking.BookingNumber;

                if (newHotelCapacity >= 0)
                {
                    hotel.Capacity = (int)newHotelCapacity;
                    db.SaveChanges();
                    db.Booking.Add(bk);
                    db.SaveChanges();
                    ViewBag.Success = "Create Successfully!";
                    return RedirectToAction("Index");
                }
                else
                {
                    var model = new Booking()
                    {
                        List = db.Hotel.Select(p => new SelectListItem { Text = p.Name, Value = p.Id.ToString() }).ToList()
                    };
                    ViewBag.Failure = "Booking Constraint: Booking capacity reached for that hotel. please choose a different hotel.";
                    return View(model);
                }
                
            }

            return View();

        }

        // GET: Bookings/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Booking booking = db.Booking.Find(id);
            if (booking == null)
            {
                return HttpNotFound();
            }
            ViewBag.HotelId = new SelectList(db.Hotel, "Id", "Name", booking.HotelId);
            ViewBag.UserId = new SelectList(db.AspNetUsers, "Id", "firstName", booking.UserId);
            return View(booking);
        }

        // POST: Bookings/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "Id,StartDate,EndDate,BookingNumber,HotelId,UserId")] Booking booking)
        {
            if (ModelState.IsValid)
            {
                var userId = User.Identity.GetUserId();
                Booking nowBooking = db.Booking.Find(booking.Id);


                if (db.Booking.Where(s => s.EndDate >= booking.StartDate && s.StartDate <= booking.EndDate && s.UserId == userId && booking.StartDate!=nowBooking.StartDate && booking.EndDate!= nowBooking.EndDate).Any())
                {
                    var model = new Booking()
                    {
                        List = db.Hotel.Select(p => new SelectListItem { Text = p.Name, Value = p.Id.ToString() }).ToList()
                    };
                    ViewBag.Failure = "Booking Constraint: There is already a booking for that date. Please choose a different date.";
                    return View(model);
                }

                Booking bk = new Booking();
                bk.HotelId = booking.HotelId;
                bk.BookingNumber = (int)booking.BookingNumber;
                bk.StartDate = (System.DateTime)booking.StartDate;
                bk.EndDate = (System.DateTime)booking.EndDate;
                bk.UserId = userId; //booking.user_id;

                //Getting the hotel
                Hotel hotel = db.Hotel.Find(booking.HotelId);

                var newHotelCapacity = hotel.Capacity + nowBooking.BookingNumber - booking.BookingNumber;

                if (newHotelCapacity >= 0)
                {
                    hotel.Capacity = (int)newHotelCapacity;
                    db.SaveChanges();
                    db.Booking.Add(bk);
                    db.Booking.Remove(nowBooking);
                    db.SaveChanges();
                    ViewBag.Success = "Edit Successfully!";
                    return RedirectToAction("Index");
                }
                else
                {
                    var model = new Booking()
                    {
                        List = db.Hotel.Select(p => new SelectListItem { Text = p.Name, Value = p.Id.ToString() }).ToList()
                    };
                    ViewBag.Failure = "Booking Constraint: Booking capacity reached for that hotel. please choose a different hotel.";
                    return View(model);
                }

            }
            ViewBag.HotelId = new SelectList(db.Hotel, "Id", "Name", booking.HotelId);
            ViewBag.UserId = new SelectList(db.AspNetUsers, "Id", "firstName", booking.UserId);
            return View(booking);
        }

        // GET: Bookings/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Booking booking = db.Booking.Find(id);
            if (booking == null)
            {
                return HttpNotFound();
            }

            //Getting the hotel
            Hotel hotel = db.Hotel.Find(booking.HotelId);

            var newHotelCapacity = hotel.Capacity + booking.BookingNumber;

            hotel.Capacity = (int)newHotelCapacity;
            db.SaveChanges();
            return View(booking);

        }

        // POST: Bookings/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            Booking booking = db.Booking.Find(id);
            db.Booking.Remove(booking);
            db.SaveChanges();
            ViewBag.Success = "Delete Successfully!";
            return View(booking);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        //GET: Bookins/Rate
        public ActionResult Rate(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }

            Hotel hotel = db.Hotel.Find(id);
            /*Booking booking = db.Bookings.Find(id);
            if (booking == null)
            {
                return HttpNotFound();
            }*/
            return View(hotel);
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Rate(int? id, Hotel hotel)
        {
            if (ModelState.IsValid)
            {
                if (hotel.RatingClass != null)
                {
                    //hotel.hotel_rating = 2;

                    Hotel hotel2 = db.Hotel.Find(id);

                    var oldRating = hotel2.RatingClass;
                    var noOfRatings = hotel2.NoR;
                    noOfRatings = noOfRatings + 1;

                    // calculating the new rating
                    var newRating = oldRating + ((hotel.RatingClass - oldRating) / noOfRatings);


                    hotel2.NoR = hotel2.NoR + 1;

                    hotel2.RatingClass = newRating;
                    //f.Name = NewName;
                    //myEntities.SaveChanges();


                    db.SaveChanges();
                    db.SaveChanges();
                    /*db.Entry(booking).State = EntityState.Modified;
                    db.SaveChanges();
                    return RedirectToAction("Index");*/

                    //ViewBag.Result = "Thank you for rating " + hotel.hotel_name;

                    TempData["shortMessage"] = "Thank you for rating " + hotel2.Name;
                }
                else
                {
                    ViewBag.Result = "Please select a rating";
                    return View(hotel);
                }
            }

            return RedirectToAction("Index");
        }
    }
}
