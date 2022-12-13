import datetime
import unittest
import appts

data = appts.ApptsData()
test_doctor_id = data.add_doctor('John', 'Doe')
date = datetime.datetime.utcnow()


class AppointmentsTest(unittest.TestCase):
    """Tests for appointments"""

    def test_add_appointment_correct(self):
        self.assertTrue(data.add_appointment({
            'doc_id': test_doctor_id,
            'date': date,
            'time': '2',
            'patient_first_name': 'Dan',
            'patient_last_name': 'Mots',
            'kind': 'Test',
        }))

    def test_get_appointments(self):
        with self.assertRaises(KeyError):
            data.get_appointments(-100, "")

    def test_get_appointment_wrong(self):
        with self.assertRaises(KeyError):
            data.get_appointments(-100, "")

    def test_get_appointment_wrong_2(self):
        with self.assertRaises(KeyError):
            data.get_appointments(-2, "")

    def test_get_appointment(self):
        with self.assertRaises(KeyError):
            data.get_appointments(test_doctor_id, "")

    def test_del_appointment_wrong(self):
        with self.assertRaises(KeyError):
            data.del_appointment(-100, "", "")

    def test_del_appointment(self):
        with self.assertRaises(KeyError):
            data.del_appointment(test_doctor_id, "", "")

    def test_del_appointment_correct(self):
        _, appt_id = data.add_appointment({
            'doc_id': test_doctor_id,
            'date': date,
            'time': '2',
            'patient_first_name': 'Dan',
            'patient_last_name': 'Mots',
            'kind': 'Test',
        })
        self.assertTrue(data.del_appointment(test_doctor_id, date, appt_id))

    def test_get_doctors_count(self):
        self.assertEquals(len(data.get_doctors()), 1)

    def test_wrong_appointment(self):
        with self.assertRaises(KeyError):
            data.add_appointment({"doc_id": -100})

    def test_wrong_empty_appointment(self):
        with self.assertRaises(KeyError):
            data.add_appointment({})

    def test_get_doctors(self):
        self.assertTrue(data.get_doctors())

    def test_check_doctors_name(self):
        d = next(iter(data.get_doctors()[0].values()))
        self.assertTrue(d['first_name'], 'John')

    def test_check_doctors_last_name(self):
        d = next(iter(data.get_doctors()[0].values()))
        self.assertTrue(d['last_name'], 'Doe')

    def test_check_doctors_full_name(self):
        d = next(iter(data.get_doctors()[0].values()))
        self.assertTrue(d['first_name'], 'John')
        self.assertTrue(d['last_name'], 'Doe')


if __name__ == '__main__':
    unittest.main()
