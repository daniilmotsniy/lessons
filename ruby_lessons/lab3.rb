def calculate(h, m_u, p_a, kr, n, theta_0)
  transmit = 0
  reflect = 0
  absorb = 0
  cos_theta = Math.cos(theta_0 * Math::PI / 180)
  n.times do
    x = 0
    theta_cos = cos_theta
    loop do
      g = rand 0.0..1.0
      dist = -Math.log(g) * m_u
      x += dist * theta_cos
      if x < 0
        reflect += 1
        break
      end
      if x > h
        transmit += 1
        break
      end
      g = rand 0.0..1.0
      if g < p_a
        absorb += 1
        break
      end
      g = rand 0.0..1.0
      omega_cos = g ** (1.0 / (kr + 1))
      g = rand 0.0..1.0
      phi_sin = Math.sin g * 2 * Math::PI
      theta_sin_sq = 1 - theta_cos * theta_cos
      omega_sin_sq = 1 - omega_cos * omega_cos
      theta_cos *= omega_cos
      theta_cos -= Math.sqrt(theta_sin_sq * omega_sin_sq) * phi_sin
    end
  end
  transmit = transmit.to_f / n
  reflect = reflect.to_f / n
  absorb = absorb.to_f / n
  puts "Вильіт вперед #{transmit}, похибка: #{Math.sqrt(transmit * (1 - transmit) / n)}\n"
  puts "Вильіт назад #{reflect}, похибка: #{Math.sqrt(reflect * (1 - reflect) / n)}\n"
  puts "Поглинання #{absorb}, похибка: #{Math.sqrt(absorb * (1 - absorb) / n)}\n"
  [transmit, reflect, absorb]

end

# monte carlo param
ns = 1_000

h = 10.0
m_u = 1.0
p_a = 0.001
kr = 5
# angle
theta_0 = 90.0

calculate(h, m_u, p_a, kr, ns, theta_0).map { |t| t.to_f / ns }
